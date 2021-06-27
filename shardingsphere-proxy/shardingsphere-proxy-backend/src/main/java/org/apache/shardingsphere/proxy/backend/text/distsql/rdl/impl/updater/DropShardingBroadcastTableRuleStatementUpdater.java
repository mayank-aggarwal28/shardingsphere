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

package org.apache.shardingsphere.proxy.backend.text.distsql.rdl.impl.updater;

import org.apache.shardingsphere.infra.distsql.RDLUpdater;
import org.apache.shardingsphere.infra.metadata.resource.ShardingSphereResource;
import org.apache.shardingsphere.proxy.backend.exception.ShardingBroadcastTableRuleNotExistsException;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.distsql.parser.statement.DropShardingBroadcastTableRulesStatement;

/**
 * Drop sharding broadcast table rule statement updater.
 */
public final class DropShardingBroadcastTableRuleStatementUpdater implements RDLUpdater<DropShardingBroadcastTableRulesStatement, ShardingRuleConfiguration> {
    
    @Override
    public void checkSQLStatement(final String schemaName, final DropShardingBroadcastTableRulesStatement sqlStatement, 
                                  final ShardingRuleConfiguration currentRuleConfig, final ShardingSphereResource resource) {
        if (null == currentRuleConfig || currentRuleConfig.getBroadcastTables().isEmpty()) {
            throw new ShardingBroadcastTableRuleNotExistsException(schemaName);
        }
    }
    
    @Override
    public boolean updateCurrentRuleConfiguration(final String schemaName, final DropShardingBroadcastTableRulesStatement sqlStatement, final ShardingRuleConfiguration currentRuleConfig) {
        currentRuleConfig.getBroadcastTables().clear();
        return false;
    }
    
    @Override
    public String getType() {
        return DropShardingBroadcastTableRulesStatement.class.getCanonicalName();
    }
}
