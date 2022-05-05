package Utils;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

public class FolderListenerUtilities {

    public static String newFileCreated(Path path, AtomicBoolean running) { //static method that returns absolute string path to newly created object
        try { //check if the given path is folder !!!should never fail, but this is an extra safety measure!!!
            boolean isFolder = (boolean) Files.getAttribute(path, "basic:isDirectory", NOFOLLOW_LINKS);
            if (!isFolder) {
                throw new IllegalArgumentException("not a folder");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        FileSystem fileSystem = path.getFileSystem(); //generating a new fileSystem

        try (WatchService watchService = fileSystem.newWatchService()) {
            path.register(watchService, ENTRY_CREATE); //watch service that watches for file creation //https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/file/WatchService.html
            WatchKey key;
            while (true && running.get()) {
                key = watchService.poll();
                Thread.sleep(100);

                WatchEvent.Kind<?> kind = null;
                if(key != null) {
                    for (WatchEvent<?> wEvent : key.pollEvents()) {
                        kind = wEvent.kind();
                        if (kind == ENTRY_CREATE) { // if creation happened
                            Path retPath = ((WatchEvent<Path>) wEvent).context();
                            return path.toString().concat("\\").concat(retPath.getFileName().toString()); // return the new file adress
                        }
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
