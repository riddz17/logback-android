package ch.qos.logback.core.property;

import ch.qos.logback.core.PropertyDefinerBase;
import ch.qos.logback.core.util.Loader;
import ch.qos.logback.core.util.OptionHelper;

import java.net.URL;

/**
 * In conjunction with {@link ch.qos.logback.core.joran.action.PropertyAction} sets
 * the named variable to "true" if the {@link #setResource(String) resource} specified
 * by the user is available on the class path, "false" otherwise.
 *
 * @see #getPropertyValue()
 *
 * @author XuHuisheng
 * @author Ceki Gulcu
 * @since 1.1.0
 */
public class ResourceExistsPropertyDefiner extends PropertyDefinerBase {

    String resourceStr;

    public String getResource() {
        return resourceStr;
    }

    /**
     * Sets the resource to search for on the class path.
     *
     * @param resource the resource path
     */
    public void setResource(String resource) {
        this.resourceStr = resource;
    }

    /**
     * Returns the string "true" if the {@link #setResource(String) resource} specified by the
     * user is available on the class path, "false" otherwise.
     *
     * @return "true"|"false" depending on the availability of resource on the classpath
     */
    public String getPropertyValue() {
        if (OptionHelper.isEmpty(resourceStr)) {
            addError("The \"resource\" property must be set.");
            return null;
        }

        URL resourceURL = Loader.getResourceBySelfClassLoader(resourceStr);
        return booleanAsStr(resourceURL != null);
    }

}
