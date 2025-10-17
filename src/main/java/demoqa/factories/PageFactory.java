package demoqa.factories;

import demoqa.exceptions.CanNotCreatePageException;
import demoqa.pages.BasePage;

public class PageFactory {

    private PageFactory() {
        // private constructor to prevent instantiation
    }

    public static <T extends BasePage> T create(Class<T> pageClass) {
        try {
            T page = pageClass.getDeclaredConstructor().newInstance();
            UtilityManager.waitUtil().waitForPageLoad();
            return page;
        } catch (Exception e) {
            throw new CanNotCreatePageException("Cannot create page: " + pageClass.getSimpleName());
        }
    }
}
