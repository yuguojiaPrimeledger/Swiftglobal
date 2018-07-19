package com.higgsblock.global.chain.app.blockchain.consensus.handler;

import com.higgsblock.global.chain.app.blockchain.Block;
import com.higgsblock.global.chain.app.blockchain.consensus.message.VotingBlockRequest;
import com.higgsblock.global.chain.app.blockchain.consensus.message.VotingBlockResponse;
import com.higgsblock.global.chain.app.blockchain.consensus.sign.service.VoteProcessor;
import com.higgsblock.global.chain.app.blockchain.listener.MessageCenter;
import com.higgsblock.global.chain.app.common.SocketRequest;
import com.higgsblock.global.chain.app.common.handler.BaseMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author yuanjiantao
 * @date 7/2/2018
 */
@Component
@Slf4j
public class VotingBlockRequestHandler extends BaseMessageHandler<VotingBlockRequest> {

    @Autowired
    private MessageCenter messageCenter;

    @Autowired
    private VoteProcessor voteProcessor;

    @Override
    protected void process(SocketRequest<VotingBlockRequest> request) {
        String sourceId = request.getSourceId();
        VotingBlockRequest data = request.getData();
        if (null == data || CollectionUtils.isEmpty(data.getBlockHashs())) {
            return;
        }
        LOGGER.info("received originalBlockRequest from {} with data {}", sourceId, data);
        data.getBlockHashs().forEach(hash -> {
            Block block = voteProcessor.getBlockCache().get(voteProcessor.getHeight(), k -> new HashMap<>()).get(hash);
            if (null != block) {
                messageCenter.unicast(sourceId, new VotingBlockResponse(block));
            }
        });


    }
}