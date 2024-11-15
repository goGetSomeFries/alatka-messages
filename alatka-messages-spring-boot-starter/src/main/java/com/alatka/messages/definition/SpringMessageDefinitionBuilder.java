package com.alatka.messages.definition;

import com.alatka.messages.core.definition.CompositeMessageDefinitionBuilder;
import org.springframework.beans.factory.InitializingBean;

public class SpringMessageDefinitionBuilder extends CompositeMessageDefinitionBuilder implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        this.build();
    }

}
