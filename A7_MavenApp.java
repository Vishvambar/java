/*
 * Maven Project — pom.xml dependency required:
 *
 * <dependency>
 *     <groupId>org.apache.commons</groupId>
 *     <artifactId>commons-lang3</artifactId>
 *     <version>3.14.0</version>
 * </dependency>
 *
 * After adding, right-click project → Maven → Update Project → ✅ Force Update
 */

import org.apache.commons.lang3.StringUtils;

public class A7_MavenApp {

    public static void main(String[] args) {

        String original = "  Hello, Maven World!  ";

        System.out.println("=== Apache Commons StringUtils Demo ===");
        System.out.println("Original       : [" + original + "]");
        System.out.println("Trimmed        : [" + StringUtils.trim(original) + "]");
        System.out.println("Reversed       : " + StringUtils.reverse(original.trim()));
        System.out.println("Is Blank?      : " + StringUtils.isBlank(original));
        System.out.println("Is Alpha?      : " + StringUtils.isAlpha("HelloWorld"));
        System.out.println("Capitalize     : " + StringUtils.capitalize("maven project"));
        System.out.println("Abbreviate     : " + StringUtils.abbreviate("Advanced Java Programming", 15));
        System.out.println("Repeat         : " + StringUtils.repeat("Java ", 3));
        System.out.println("Contains Ignore: " + StringUtils.containsIgnoreCase(original, "maven"));

        System.out.println("\nMaven dependency loaded successfully!");
    }
}
