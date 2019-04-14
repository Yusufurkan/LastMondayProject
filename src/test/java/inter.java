public class inter {

    static boolean check_bal(String s) {
        int i, top;
        char[] chars = new char[100000];
        top = -1;

        //iterate through the chars in the string
        for (i = 0; i < s.length(); i++) {
            // check if it's bracket

            if ((s.charAt(i) == '[') || (s.charAt(i) == '{') || (s.charAt(i) == '(')) {
                chars[++top] = s.charAt(i);
            }
            //if opening bracket, push
            if (s.charAt(i) == ']') {
                if (top >= 0 && chars[top] == '[')
                    top -= 1;
                    //check if top>=0, since starting element cannot be a closing bracket
                else return false;
            }

            if (s.charAt(i) == '}') {
                if (top >= 0 && chars[top] == '{') top -= 1;
                else return false;
            }// if

            if (s.charAt(i) == ')') {
                if (top >= 0 && chars[top] == '(') top -= 1;
                else return false;
            }// if

        }
        if (top == -1) return true;
        else return false;
    }

}