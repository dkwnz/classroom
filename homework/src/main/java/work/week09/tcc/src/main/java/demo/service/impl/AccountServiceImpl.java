/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.service.impl;

import demo.pojo.Change;
import demo.service.ChangeService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lw1243925457
 */

@Slf4j
@Service("accountService")
public class AccountServiceImpl implements ChangeService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean exChange(Change change) {
        int count = jdbcTemplate.update("update `himly_dubbo_account` set us_account = ? where id=?", change.getUs_wallet(), change.getId());
        log.info("change account : + " + change.getId() + " try result: " + count);
        return count > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Change change) {
        log.info("confirm change : {}", change.toString());
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Change account) {
        log.info("cancel change : {}", account.toString());
        return true;
    }

}
