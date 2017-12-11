import java.util.*;

class FileSystem {
    Map<String, Integer> pathMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        pathMap.put("", 0);
    }

    public boolean create(String path, int value) {
        if (pathMap.containsKey(path)) {
            return false;
        }
        int end_pos = path.lastIndexOf("/");
        String key = path.substring(0, end_pos);
        if (!pathMap.containsKey(key)) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }

    public boolean set(String path, int value) {
        if (!pathMap.containsKey(path)) {
            return false;
        }
        pathMap.put(path, value);
        return true;
    }

    public Integer get(String path) {
        return pathMap.get(path);
    }

    public final static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.create("/a", 3));
        System.out.println(fs.get("/a"));
        System.out.println(fs.create("/b/c", 3));
        System.out.println(fs.get("/b/c"));
    }
}

