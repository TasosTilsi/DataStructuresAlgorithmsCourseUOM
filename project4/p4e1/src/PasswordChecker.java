public class PasswordChecker {

    private SeparateChainingHashST<String, Integer> st;

    /**
     * constructor
     */
    public PasswordChecker() {
        st = new SeparateChainingHashST<>();
        createDictionary();
    }

    /**
     * create a dictionary in a separateChainingHashST object
     */
    public void createDictionary() {
//        StdOut.println("Please Insert a List with words you want to put into the dictionary");
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
//        StdOut.println(st.keys());
    }

    /**
     * Checks the validity of the given password
     *
     * @param password
     * @return boolean
     */
    public boolean isValid(String password) {

        //checking length greater than 8
        if (password.length() < 8) {
            return false;
        }

        //checking password if exists in dictionary
        if (st.contains(password)) {
            return false;
        }

        //checking passwords first word not containing in dictionary
        if (password.matches(".*\\d+") && st.contains(password.split("\\d+")[0])){
            return false;
        }

        //checking password if contains 2 words separated by a digit
        if (contains2WordsSeparatedByDigit(password)) {
            return false;
        }

        //if none of the above return valid password
        return true;
    }

    /**
     * A method to check if there is a separation in 2 dictionary words with a digit
     * @param password
     * @return
     */
    private boolean contains2WordsSeparatedByDigit(String password) {
        String firstString = "";
        String secondString = "";
        boolean wordsFlag;

        if (password.matches(".*\\d+.*")) {
            firstString = password.split("\\d+")[0];
            try {
                secondString = password.split("\\d+")[1];
            }catch (Exception e){
                secondString = "";
            }

        }

        if (st.contains(firstString) && st.contains(secondString)) {
            wordsFlag = true;
        } else {
            wordsFlag = false;
        }

        return wordsFlag;
    }
}
