class Solution {
    public String parseCSV(String str) {
        StringBuilder result = new StringBuilder();

        boolean isQuoted = false;
        int i = 0;
        while (i < str.length()) {
            if (isQuoted) {
                if (str.charAt(i) == '\"') {
                    if (i + 1 < str.length() && str.charAt(i + 1) == '\"') {
                        result.append('\"');
                        ++i;
                    } else {
                        isQuoted = false;
                    }
                } else {
                    result.append(str.charAt(i));
                }
            } else {
                if (str.charAt(i) == '\"') {
                    isQuoted = true;
                } else if (str.charAt(i) == ',') {
                    result.append('|');
                } else {
                    result.append(str.charAt(i));
                }
            }
            ++i;
        }

        return result.toString();
    }

    public final static void main(String[] arg) {
        /*
 *Input: csvformat


"
 *
 *      */

        Solution s = new Solution();
        System.out.println(s.parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1"));
        System.out.println(s.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(s.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1 \"\"\"Alexandra Alex\"\"\""));

    }
}
