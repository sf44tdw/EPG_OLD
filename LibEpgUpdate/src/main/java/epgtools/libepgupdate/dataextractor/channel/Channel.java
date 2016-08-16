package DataExtractor.Channel;

import java.util.Objects;
import DataExtractor.EpgData;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * チャンネル情報保持用
 * 
* @author dosdiaopfhj
 */
public final class Channel implements EpgData {

// チャンネルid
    private final String id;

// 物理チャンネル番号
    private final int physicalChannelNumber;

// 放送局名
    private final String broadcastingStationName;

    /**
     *
     * @param id チャンネルID
     * @param physicalChannelNumber 物理チャンネル番号
     * @param broadcastingStationName 放送局名
     */
    public Channel(String id, int physicalChannelNumber, String broadcastingStationName) throws IllegalArgumentException {
        if (id != null && !"".equals(id)) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("チャンネルIDがありません。");
        }
        if (physicalChannelNumber >= 0) {
            this.physicalChannelNumber = physicalChannelNumber;
        } else {
            throw new IllegalArgumentException("物理チャンネル番号が0未満です。");
        }
        if (broadcastingStationName != null && !"".equals(broadcastingStationName)) {
            this.broadcastingStationName = broadcastingStationName;
        } else {
            throw new IllegalArgumentException("放送局名がありません。");
        }

    }

    /**
     * @return チャンネルID
     */
    @Override
    public synchronized String getId() {
        return id;
    }

    /**
     * @return 物理チャンネル番号
     */
    public synchronized int getPhysicalChannelNumber() {
        return physicalChannelNumber;
    }

    /**
     * @return 放送局名
     */
    public synchronized String getBroadcastingStationName() {
        return broadcastingStationName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + this.physicalChannelNumber;
        return hash;
    }

    /**
     * 保持している値がすべて等しければtrue
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Channel other = (Channel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.physicalChannelNumber != other.physicalChannelNumber) {
            return false;
        }
        if (!Objects.equals(this.broadcastingStationName, other.broadcastingStationName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "channel{" + "チャンネルid=" + id + ", 物理チャンネル番号=" + physicalChannelNumber + ", 放送局名=" + broadcastingStationName + '}';
    }
}
