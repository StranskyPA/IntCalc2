package intcalc;

public class newParser extends edu.hendrix.grambler.Grammar {
    public newParser() {
        super();
        addProduction("lines", new String[]{"lines", "line"}, new String[]{"line"});
        addProduction("line", new String[]{"assn", "newline"}, new String[]{"assn"});
        addProduction("assn", new String[]{"var", "sp", "op3", "sp", "expr"}, new String[]{"expr"});
        addProduction("op3", new String[]{"'='"});
        addProduction("expr", new String[]{"expr", "sp", "op", "sp", "expr2"}, new String[]{"expr2"});
        addProduction("op", new String[]{"'+'"}, new String[]{"'-'"});
        addProduction("expr2", new String[]{"expr2", "sp", "op2", "sp", "paren"}, new String[]{"paren"});
        addProduction("op2", new String[]{"'*'"}, new String[]{"'/'"});
        addProduction("paren", new String[]{"'('", "expr", "')'"}, new String[]{"'['", "expr", "']'"}, new String[]{"num"});
        addProduction("num", new String[]{"\"\\d+\""}, new String[]{"var"});
        addProduction("var", new String[]{"\"[a-zA-Z]+\""});
        addProduction("newline", new String[]{"'\r\n'"}, new String[]{"'\n'"});
        addProduction("sp", new String[]{"\"\\s*\""});
    }
}

