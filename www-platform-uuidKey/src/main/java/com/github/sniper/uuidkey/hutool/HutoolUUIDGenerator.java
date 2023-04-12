package com.github.sniper.uuidkey.hutool;

import com.github.sniper.uuidkey.base.UUIDGenerator;

/**
 * @author Sniper_lw
 * @version 1.0.0
 * @description TODO
 * @date 2023/4/12 10:18
 */
public class HutoolUUIDGenerator implements UUIDGenerator<String> {

    static cn.hutool.core.lang.generator.UUIDGenerator generator =new cn.hutool.core.lang.generator.UUIDGenerator();

    @Override
    public String nextId() {
        return generator.next();
    }


}
