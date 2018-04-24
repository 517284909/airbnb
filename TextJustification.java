import java.util.*;

class Solution {

    public String textJustification(String[] rows, int width) {
        width -= 2;
        StringBuilder builder = new StringBuilder();
        for (String r: rows) {
            builder.append('+');
            for (int i = 0; i < width; i++)
                builder.append('-');
            builder.append('+').append('\n');

            String[] tokens = r.split(" ");
            StringBuilder buf = new StringBuilder();
            buf.append(tokens[0]);
            int i = 1;
            while (i < tokens.length) {
                if (buf.length() + 1 + tokens[i].length() <= width) {
                    buf.append(' ').append(tokens[i]);
                } else {
                    for (int j = buf.length(); j < width; j++)
                        buf.append(' ');
                    builder.append('|').append(buf).append('|').append('\n');
                    buf.setLength(0);
                    buf.append(tokens[i]);
                }
                ++i;
            }
            for (int j = buf.length(); j < width; j++)
                buf.append(' ');
            builder.append('|').append(buf).append('|').append('\n');
        }
        builder.append('+');
        for (int i = 0; i < width; i++)
            builder.append('-');
        builder.append('+');
        return builder.toString();
    }

    public final static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.textJustification(new String[]{"first word", "my second sentence", "now it's third"}, 10));
    
    }
}

