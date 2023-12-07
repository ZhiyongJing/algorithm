package template;

public class StrStr {
    public static boolean containSubstr(String source, String target){
        if (source == null || target == null || source.length() == 0 || target.length() == 0){
            return false;
        }
        for (int i = 0; i < source.length(); i++){
            int j = 0;
            for (j = 0; j < target.length() && (i + j) < source.length(); j++){
                if (source.charAt(i+j) != target.charAt(j)){
                    break;
                }
            }
            if (j == target.length()){
                return true;
            }
        }
        return  false;
    }
    public static void main(String[] args) {
        String source = "abcd";
        String target = "bc";

        System.out.println(containSubstr(source, target));
    }
}
