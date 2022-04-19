package bringItOn;

public class TextVariables {
    public static final String CODE_FOR_TEXT_AREA_BRING_IT_ON = """
            git config --global user.name  "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force""";
    public static final String PASTE_TITLE_BRING_IT_ON = "how to gain dominance among developers";
}
