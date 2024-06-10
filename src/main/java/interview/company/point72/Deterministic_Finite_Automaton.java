package interview.company.point72;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Deterministic_Finite_Automaton {
    /**
     * 状态转移规则, 当前状态 + 输入类型 -> 下一个状态
     * 第一层Map, key: 当前状态
     * 第二层Map, key: 输入类型; value: 下一个状态
     */
    /**
     * states Q = [ONE, TWO, THREE]
     * input string {a, b}
     * initial state: ONE
     * transition functions
     * F(ONE, A) -> ONE
     * F(ONE, B) -> TWO
     * F(TWO, A) -> TWO
     * F(TWO, B) -> THREE
     * F(THREE, A) -> THREE
     * F(THREE, B) -> ONE
     */

        public enum STATE{
            ONE("reject"),
            TWO("reject"),
            THREE("accept");
            private String value;
            STATE(String value){
                this.value = value;
            }

            public String getValue() {
                return value;
            }
        }
        public enum INPUT{
            A('a'),
            B('b');
            //OTHER("-");
            private char input;
            INPUT(char input){
                this.input = input;
            }
            public char getInput(){
                return input;
            }
        }

        private  Map<STATE, Map<INPUT, STATE>> transitFunction = new HashMap<>();
        public Deterministic_Finite_Automaton(){
            Map<INPUT, STATE> inputStateOneMap = new HashMap<>();
            inputStateOneMap.put(INPUT.A, STATE.ONE);
            inputStateOneMap.put(INPUT.B, STATE.TWO);
            transitFunction.put(STATE.ONE, inputStateOneMap);

            Map<INPUT, STATE> inputStateTwoMap = new HashMap<>();
            inputStateTwoMap.put(INPUT.A, STATE.TWO);
            inputStateTwoMap.put(INPUT.B, STATE.THREE);
            transitFunction.put(STATE.TWO, inputStateTwoMap);

            Map<INPUT, STATE> inputStateThreeMap = new HashMap<>();
            inputStateThreeMap.put(INPUT.A, STATE.THREE);
            inputStateThreeMap.put(INPUT.B, STATE.ONE);
            transitFunction.put(STATE.THREE, inputStateThreeMap);
        }
        public void isDFA(String input){
            if(input == null || input == ""){
                System.out.println("reject");
                return;
            }
            STATE initiateState = STATE.ONE;
            STATE stateTemp = initiateState;

            char[] charArray = input.toCharArray();
            for(int i = 0; i< charArray.length; i++){
                Map<INPUT, STATE> inputstateMap = transitFunction.get(stateTemp);

                if( charArray[i] == 'a'){
                    stateTemp = inputstateMap.get(INPUT.A);
                }
                else if( charArray[i] == 'b'){
                    stateTemp = inputstateMap.get(INPUT.B);
                }
                else {
                    System.out.println("reject");
                    return;
                }
                if( i == charArray.length - 1){
                    if(stateTemp.getValue() == STATE.THREE.getValue()){
                        System.out.println("accept");
                        return;
                    }
                }
            }
            System.out.println("reject");


        }

        public static void main(String[] args) {
            Deterministic_Finite_Automaton dfa = new Deterministic_Finite_Automaton();
//            String[] list = new String[]{"abab", "ab", "abb"};
//            for(String s: list){
//                dfa.isDFA(s);
//            }
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            dfa.isDFA(input);

        }
    }

