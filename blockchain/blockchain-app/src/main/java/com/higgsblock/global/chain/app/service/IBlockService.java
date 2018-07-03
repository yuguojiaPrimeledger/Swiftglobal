package com.higgsblock.global.chain.app.service;

import com.higgsblock.global.chain.app.blockchain.Block;

import java.util.List;

/**
 * @author Zhao xiaogang
 * @date 2018-05-21
 */
public interface IBlockService {

    /**
     * Check if the block with this block hash exist in the database
     * @param height block height
     * @param blockHash the block hash
     * @return boolean
     */
    boolean isExistInDB(long height, String blockHash);

    /**
     * Check if the block exist in the database
     *
     * @param block the block
     * @return boolean
     */
    boolean isExist(Block block);

    /**
     * Check if the pre-block exist in the database
     *
     * @param block the block
     * @return boolean
     */
    boolean preIsExistInDB(Block block);

    /**
     * Get block by block hash
     *
     * @param blockHash the block hash
     * @return Block
     */
    Block getBlockByHash(String blockHash);

//    /**
//     * Get the max height of the block
//     *
//     * @return long
//     */
//    long getMaxHeight();
//
//    /**
//     * Get last best block
//     *
//     * @return Block
//     */
//    Block getLastBestBlock();

    /**
     * Get blocks by height
     *
     * @param height height
     * @return List<Block>
     */
    List<Block> getBlocksByHeight(long height);

    /**
     * Get blocks except the block with the height and the block hash
     *
     * @param height          the height
     * @param exceptBlockHash the excluding block hash
     * @return List<Block>
     */
    List<Block> getBlocksExcept(long height, String exceptBlockHash);

    /**
     * Get the best block by height
     *
     * @param height height
     * @return Block
     */
    Block getBestBlockByHeight(long height);

    /**
     * Save the block, block index , transaction index, utxo and scores
     *
     * @param block     the block
     * @param blockHash the block hash
     * @return block
     * @throws Exception
     */
    Block saveBlockCompletely(Block block, String blockHash) throws Exception;

    /**
     * print the blocks
     *
     * @return void
     */
    void printAllBlockData();

    /**
     * Check the block numbers
     *
     * @return boolean
     */
    boolean checkBlockNumbers();

}