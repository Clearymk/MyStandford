import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.tregex.TregexMatcher;
import edu.stanford.nlp.trees.tregex.TregexPattern;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Parser {

    private final static String PCG_MODEL = "src/main/resources/englishPCFG.ser.gz";

    private final TokenizerFactory<CoreLabel> tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "invertible=true");

    private final LexicalizedParser parser = LexicalizedParser.loadModel(PCG_MODEL);

    public Tree parse(String str) {
        List<CoreLabel> tokens = tokenize(str);
        Tree tree = parser.apply(tokens);
        return tree;
    }

    private List<CoreLabel> tokenize(String str) {
        Tokenizer<CoreLabel> tokenizer =
                tokenizerFactory.getTokenizer(
                        new StringReader(str));
        return tokenizer.tokenize();
    }

    public static void main(String[] args) {
        File policyFile = new File("src/main/resources/age.of.civilizations.jakowski.lite/age.of.civilizations.jakowski/policy.txt");
        StringBuilder policy = new StringBuilder();

        try {
            BufferedReader in = null;
            in = new BufferedReader(new FileReader(policyFile));
            String str;
            while ((str = in.readLine()) != null) {
                policy.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Parser parser = new Parser();
        Tree tree = parser.parse(policy.toString());

        List<String> result = new ArrayList<>();
        TregexPattern pattern = TregexPattern.compile("@NP");
        TregexMatcher matcher = pattern.matcher(tree);
        while (matcher.find()) {
            Tree match = matcher.getMatch();
            List<Tree> leaves = match.getLeaves();

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("npverbs.txt", true));
                out.write(leaves.toString().replace("[", "").replace("]", "").replace(",", "") + "\n");
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(leaves);
        }
    }
}