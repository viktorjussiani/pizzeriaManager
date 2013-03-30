package controllers.pizzeria.global;

import java.lang.reflect.Method;

import play.mvc.Content;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Generic controller that contains generic methods common in all controllers
 *
 * @author anderson.vasconcelos
 *
 */
public class GenericController extends Controller {

	public static Result renderTemplate(String templatePath) {
		try {
			templatePath = templatePath.replaceAll("/", "\\.");
			String templateClassName = "views.html.".concat(templatePath);
			Class<?> c = Class.forName(templateClassName);
			Method method = findMethod(c, "render");
			Object object = method.invoke(null);
			return ok((Content) object);
		} catch (Exception e) {
			return badRequest("Could not load template for rendering");
		}
	}

	public static Method findMethod(Class<?> clazz, String methodName) throws NoSuchMethodException {
		Class<?>[] params = new Class[0];
		Method method;

		try {
			method = clazz.getDeclaredMethod(methodName, params);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			if (clazz.getSuperclass() == null) {
				throw e;
			}
			method = findMethod(clazz.getSuperclass(), methodName);
		}
		return method;
	}
}