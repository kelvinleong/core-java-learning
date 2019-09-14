import algorithms.MergeStrings;

public class Application {

    private static void mergeStrings() {
        MergeStrings m = new MergeStrings();
        String r = m.merge(new String[]{"apple", "iphonex"});
        System.out.println("Result:: " + r);
    }

    public static void main(String[] args) {
        mergeStrings();
    }
}
