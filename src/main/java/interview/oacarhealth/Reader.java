package interview.oacarhealth;

import java.util.HashMap;
import java.util.Map;


public class Reader {
    private  Map<String, Integer> m;
    public Reader(){
        m = new HashMap<>();
    }

    public boolean checkValidation(String cardId, int time) {
        if(this.m.containsKey(cardId)){
            if(this.m.get(cardId) + 10 > time){
                return false;
            }
            else{
                this.m.put(cardId, time);
                return true;
            }
        }
        else {
            this.m.put(cardId, time);
            return true;
        }

    }

    public static void main(String[] args) {
        Reader r = new Reader();
        System.out.println(r.checkValidation("foo", 1));
        System.out.println(r.checkValidation("bar", 2));
        System.out.println(r.checkValidation("foo", 3));
        System.out.println(r.checkValidation("bar", 8));
        System.out.println(r.checkValidation("foo", 10));
        System.out.println(r.checkValidation("foo", 11));
    }
}

