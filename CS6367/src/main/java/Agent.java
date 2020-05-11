package CS6367_Phase1;

import java.util.HashSet;
import java.io.File;
import java.io.FilenameFilter;
import java.lang.instrument.Instrumentation;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {

        String p = getPath()+File.separator;
        inst.addTransformer(new MyClassFileTransformer(p));
    }

    private static String getPath() {
        int res = 0;
        String outPath = null;
        StringBuilder Path =  new StringBuilder();
        Path.append("src");
        Path.append(File.separator);
        Path.append("main");
        Path.append(File.separator);
        Path.append("java");
        
        File rootPath = new File(Path.toString());
        
        File[] list = rootPath.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return !name.equals(".DS_Store");
            }
        });
        
        HashSet<String> paths = new HashSet<String>();
        assert list != null;
        getAllPath(list, paths);
        for (String path : paths) {

            int temp = countMatches(path);
            if (res == 0 || temp<res){
                res = temp;
                outPath = path;
            }
        }
        return outPath;
    }

 
    
    private static void getAllPath(File[] files, HashSet<String> paths) {
        for (File file : files) {
            if (file.isDirectory()) {
                getAllPath(file.listFiles(), paths);
            } 
            else if(!file.isDirectory() && file.getName().endsWith(".java")){
                    String parent_path = file.getParent();
                    parent_path = parent_path.substring(14);
                    paths.add(parent_path);
                }
            }
        return;
    }



    private static int countMatches(String text) {
        if (isEmpty(text) || isEmpty(File.separator)) {
            return 0;
        }
        
        int index = 0, count = 0;
        
        do{
            index = text.indexOf(File.separator, index);
            if (index == -1)
            	break;
            	
            else if(index!=-1) {
                index += File.separator.length();
                count=count+1;
            }
        }while(true);
        
        return count;
    }
    
    private static boolean isEmpty(String s) {
    	if(s==null)
    		return true;
    	
    	if(s.length()==0)
    		return true;
    	
    	return false;
    }

}


