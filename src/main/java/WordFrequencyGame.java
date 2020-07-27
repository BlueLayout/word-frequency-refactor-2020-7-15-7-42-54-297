import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String BLANK_SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {

        if (sentence.split(SPACE_PATTERN).length == 1) {
            return sentence + " 1";
        } else {

            try {

                List<WordInfo> wordInfoLists = calculateWordFrequency(sentence);

                wordInfoLists.sort((firstWordInfo, secendWordInfo) -> secendWordInfo.getWordCount() - firstWordInfo.getWordCount());

                return generateWordFrequencyResult(wordInfoLists);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String generateWordFrequencyResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String wordInfoCount = wordInfo.getValue() + BLANK_SPACE + wordInfo.getWordCount();
            joiner.add(wordInfoCount);
        }
        return joiner.toString();
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {
        List<WordInfo> wordInfos = new ArrayList<>();
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        for (String uniqueWord : new HashSet<>(words)
        ) {
            int count = (int) words.stream().filter(word -> word.equals(uniqueWord)).count();
            WordInfo wordInfo = new WordInfo(uniqueWord, count);
            wordInfos.add(wordInfo);
        }
        return wordInfos;
    }
}
