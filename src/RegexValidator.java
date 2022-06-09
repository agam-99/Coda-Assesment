import pojos.FieldType;

import java.util.regex.Pattern;

public class RegexValidator {

    public static final Pattern EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern PHONE_NO_REGEX = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");
    public static final Pattern NUMBER_REGEX = Pattern.compile("[0-9]+");
    public static final Pattern WEB_LINK_REGEX = Pattern.compile("https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]*)");

    public static FieldType getFieldType(String input){

        input = input.trim();
        if(EMAIL_ADDRESS_REGEX.matcher(input).matches()){
            return FieldType.EMAIL;
        }

        if(PHONE_NO_REGEX.matcher(input).matches()){
            return FieldType.PHONE_NUMBER;
        }

        if(NUMBER_REGEX.matcher(input).matches()){
            return FieldType.NUMBER;
        }

        if(WEB_LINK_REGEX.matcher(input).matches()){
            return FieldType.WEB_LINK;
        }

        return FieldType.STRING;
    }
}
