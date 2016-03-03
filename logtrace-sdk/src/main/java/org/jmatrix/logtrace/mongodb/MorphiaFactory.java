package org.jmatrix.logtrace.mongodb;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * Morphia Factory
 *
 * @author jmatrix
 * @date 16/3/1
 */
public class MorphiaFactory extends AbstractFactoryBean<Morphia> {

    private Logger logger = LoggerFactory.getLogger(MorphiaFactory.class);

    /**
     * Morphia scan package name list, you can specify more than one package name by separating each with
     * a comma. example : (packageName1,packageName2,...)
     */
    private String packageNames;

    /**
     * specifies whether to ignores class that can't be mapped
     */
    private boolean ignoreInvalidClasses;

    @Override
    public Class<?> getObjectType() {
        return Morphia.class;
    }

    @Override
    protected Morphia createInstance() throws Exception {
        Morphia morphia = new Morphia();
        if (packageNames == null || packageNames.length() == 0) {
            logger.warn("the package name is not specified.");
            return null;
        }
        String packageNameArr[] = StringUtils.split(this.packageNames, ",");
        for (String packageName : packageNameArr) {
            morphia.mapPackage(packageName, ignoreInvalidClasses);
        }
        return morphia;
    }

    public void setPackageNames(String packageNames) {
        this.packageNames = packageNames;
    }

    public void setIgnoreInvalidClasses(boolean ignoreInvalidClasses) {
        this.ignoreInvalidClasses = ignoreInvalidClasses;
    }
}
