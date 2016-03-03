package org.jmatrix.logtrace.mongodb;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jmatrix
 * @date 16/3/3
 */
public class MapBeanContext {

    private Map<String, String> conditions = new HashMap<>();

    public MapBeanContext addEqual(String field, String value) {
        putCondition(ConditionDesc.EQUAL, field, value);
        return this;
    }

    public MapBeanContext addLessThan(String field, String value) {
        putCondition(ConditionDesc.LESSTHAN, field, value);
        return this;
    }

    public MapBeanContext addLessOrEqual(String field, String value) {
        putCondition(ConditionDesc.LESSOREQUAL, field, value);
        return this;
    }

    public MapBeanContext addMoreThan(String field, String value) {
        putCondition(ConditionDesc.MORETHAN, field, value);
        return this;
    }

    public MapBeanContext addMoreOrEqual(String field, String value) {
        putCondition(ConditionDesc.MOREOREQUAL, field, value);
        return this;
    }

    public MapBeanContext addNoEqual(String field, String value) {
        putCondition(ConditionDesc.NOEQUAL, field, value);
        return this;
    }

    public MapBeanContext addIn(String field, String value) {
        putCondition(ConditionDesc.IN, field, value);
        return this;
    }

    private void putCondition(ConditionDesc conditionDesc, String field, String value) {
        conditions.put(field + " " + conditionDesc.getDesc(), value);
    }

    public Map<String, String> getConditions() {
        return conditions;
    }

    private enum ConditionDesc {
        EQUAL("="), LESSTHAN("<"), LESSOREQUAL("<="), MORETHAN(">"), MOREOREQUAL(">="),
        NOEQUAL("!="), IN("in");
        private String desc;

        ConditionDesc(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }
}
