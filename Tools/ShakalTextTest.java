import org.telegram.messenger.ShakalText;

public final class ShakalTextTest {
    public static void main(String[] args) {
        check("Привет, АВТОР! Ты жжёшь 🐺", "Превед, АФФТАР! Ты жжош 🐺");
        check("https://site.test/привет @автор #медведь /привет 65/100 к17",
                "https://site.test/привет @автор #медведь /привет 65/100 к17");
        check("  ещё,  человек\n", "  исчо,  чилавег\n");
        check(" ", " ");
        System.out.println("ShakalText: passed");
    }

    private static void check(String original, String expected) {
        StringBuilder draft = new StringBuilder(original);
        ShakalText.editFromEnd(original, draft::replace);
        if (!expected.equals(ShakalText.transform(original)) || !expected.contentEquals(draft)) {
            throw new AssertionError(draft.toString());
        }
    }
}
