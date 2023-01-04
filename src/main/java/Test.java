import edu.stanford.nlp.parser.Parser;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;

import edu.stanford.nlp.trees.*;

import edu.stanford.nlp.util.*;


import java.util.List;

import java.util.Properties;


public class Test {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("annotators", "tokenize, ssplit, parse");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(properties);


//        Annotation document =
//                new Annotation("My dog also likes eating sausage.");
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//        pipeline.annotate(document);
//
//        String result = null;
//        String regex = "\\(NP \\s (?: \\( .+? \\) )* \\)"; // an NP contains an arbitrary number of sub-phrases
//        Pattern patt = Pattern.compile(regex, Pattern.COMMENTS);
//
//
//        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
//            Tree constituencyParse = sentence.get(TreeCoreAnnotations.TreeAnnotation.class);
//            System.out.println(constituencyParse);
//            String parse = constituencyParse.toString();
//            Matcher match = patt.matcher(parse);
//            while (match.find() && result == null) {
//                result = match.group();
//            }
//            if (result != null) {
//                System.out.println(result);
//            }
//        }
    }


}
