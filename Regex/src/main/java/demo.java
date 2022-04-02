import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class demo {
    private String transfer(String s) {
        Pattern p=Pattern.compile("[A-Za-z]+:");
        Matcher m=p.matcher(s);
        while(m.find()) {
            String group = m.group();
            String groupReplace = "\"" + group.replace(":","\":");
            s = s.replace(group, groupReplace);
        }
        return s.replace("_o","\"_o\"");
    }
}
