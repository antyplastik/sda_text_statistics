package language;

import com.detectlanguage.errors.APIError;
import text_analyzers.Analyzer;
import text_analyzers.LetterFrequency;

import com.detectlanguage.DetectLanguage;

import java.util.HashMap;
import java.util.Map;

public class LanguageDetection {

    private HashMap <String, String> langDetectAPIdict;
    private String apiKey = "980a6dd66173cdf8739a042175a572a0";

    public LanguageDetection() {
    }


    public String offlineAnalyze(LetterFrequency letterFrequency, MultiLanguageOfflineList availableLanguages) {
//        HashMap<String, Double> stdDeviationsOfLanguages = new HashMap<>();
        HashMap<String, Double> meanSquaredErrForLanguages = new HashMap<>();
        HashMap<String, Double> freqMapFromText = letterFrequency.getLettersFreq();
//        double letterFreqAverage = letterFrequency.getLetterFrequencyAverage();
        double tmpMeanSquaredErr = 0;
        double meanSquaredErrMIN = 0;
        int tmpIndex = 0;
        String result = "";


        for (Language language : availableLanguages.getAvailableLanguages()) {
            HashMap<String, Double> referenceLanguageMap = language.getLetterStat();
            HashMap<String, Double> tempMeanSquareMap = new HashMap<>();
            for (Map.Entry<String, Double> entry : freqMapFromText.entrySet()) {
                String tmpStr = entry.getKey();

                tmpIndex = 0;
                if (referenceLanguageMap.containsKey(tmpStr)) {
                    double lettValFromRef = referenceLanguageMap.get(tmpStr);
                    double lettValFromText = freqMapFromText.get(tmpStr);
//                    tmpMeanSquaredErr = tmpMeanSquaredErr + Math.pow((lettValFromRef - lettValFromText), 2);
                    tempMeanSquareMap.put(entry.getKey(), Math.pow((lettValFromRef - lettValFromText), 2));
//                    tmpIndex++;
                }
            }
            tmpMeanSquaredErr = tempMeanSquareMap.entrySet().stream()
                    .mapToDouble(entry -> entry.getValue())
                    .sum();
            tmpMeanSquaredErr = tmpMeanSquaredErr / tempMeanSquareMap.size();
            meanSquaredErrForLanguages.put(language.getLanguageLabel(), tmpMeanSquaredErr);
            tempMeanSquareMap.clear();
        }

        tmpIndex = 0;
        for (Map.Entry entry : meanSquaredErrForLanguages.entrySet()) {
            double tmp = (double) entry.getValue();
            if (tmp < meanSquaredErrMIN || tmpIndex == 0) {
                meanSquaredErrMIN = tmp;
                result = entry.getKey().toString();
            }
            tmpIndex++;
        }

        return result;
    }

    public String onlineAnalyze(String string) {
        DetectLanguage.ssl = true;
        DetectLanguage.apiKey = apiKey;

        try {
            return DetectLanguage.simpleDetect(string);
        } catch (APIError apiError) {
            apiError.printStackTrace();
        }
        return "";
    }

    public String useAPIdict(String apiShortcut){
        return langDetectAPIdict.get(apiShortcut);
    }

    public void addToAPIdict(String strFromFile){
        langDetectAPIdict = new HashMap<>();

        String[] shortLangLines = strFromFile.split("\n");
        for(String line : shortLangLines){
            String[] lineArr = line.split(",");
            langDetectAPIdict.put(lineArr[0],lineArr[1]);
        }
    }
}
