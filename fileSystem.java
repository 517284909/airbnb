import java.util.*;

class MyRunnable implements Runnable {
   public void run() {
      System.out.println("MyRunnable running");
   }
}

class FileSystem {
    Map<String, Integer> pathMap;
    Map<String, Runnable> callbackMap;

    public FileSystem() {
        pathMap = new HashMap<>();
        pathMap.put("", 0);
        callbackMap = new HashMap<>();
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

        String recurPath = path;
        while (recurPath.length() > 0) {
            if (callbackMap.containsKey(recurPath))
                callbackMap.get(recurPath).run();
            int lastIndex = recurPath.lastIndexOf("/");
            recurPath = recurPath.substring(0, lastIndex);
        }

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

    public boolean watch(String path, Runnable callback) {
        if (!pathMap.containsKey(path)) return false;
        callbackMap.put(path, callback);
        return true;
    }

    public void print(String path) {
        System.out.println("watch: " + path);
        //return "h";
    }

    public final static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.create("/a", 3));
        System.out.println(fs.watch("/a", new MyRunnable()));
        System.out.println(fs.get("/a"));
        System.out.println(fs.create("/a/z", 4));
        System.out.println(fs.watch("/a/z", new MyRunnable()));

        System.out.println(fs.create("/b/c", 3));
        System.out.println(fs.get("/b/c"));
    }
}

