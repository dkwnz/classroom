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
import demo.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    final private ChangeService accountService;


    @Autowired(required = false)
    public TransactionServiceImpl(ChangeService accountService) {
        this.accountService = accountService;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void transaction() {
        transactionA();
        transactionB();
    }

    private void transactionA() {
        Change change = new Change();
        change.setId(1L);
        change.setUs_wallet(-1L);
        change.setCny_wallet(7L);
        accountService.exChange(change);
    }

    private void transactionB() {
        Change change = new Change();
        change.setId(2L);
        change.setUs_wallet(1L);
        change.setCny_wallet(-7L);
        accountService.exChange(change);
    }

    public void confirmOrderStatus() {
        log.info("confirm change");
    }

    public void cancelOrderStatus() {
        log.info("cancel change");
    }
}
