package com.example.adolfo.dota2proplayer;

import java.io.Serializable;

/**
 * Created by adolfo on 16/03/18.
 */

public class Player implements Serializable {

    private static final long serialVersionUID = -1600995861319288840L;

    private String mSteamID;
    private String mAvatarMedium;
    private String mAvatarFull;
    private String mPersonaName;
    private String mName;
    private String mTeamName;
    private String mTeamTag;
    private String mCountry;

    public Player(String SteamID, String AvatarMedium, String AvatarFull, String PersonaName, String Name, String TeamName, String TeamTag, String Country) {
        this.mSteamID = SteamID;
        this.mAvatarMedium = AvatarMedium;
        this.mAvatarFull = AvatarFull;
        this.mPersonaName = PersonaName;
        this.mName = Name;
        this.mTeamName = TeamName;
        this.mTeamTag = TeamTag;
        this.mCountry = Country;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSteamID() {
        return mSteamID;
    }

    public String getAvatarMedium() {
        return mAvatarMedium;
    }

    public String getAvatarFull() {
        return mAvatarFull;
    }

    public String getPersonaName() {
        return mPersonaName;
    }

    public String getName() {
        return mName;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public String getTeamTag() {
        return mTeamTag;
    }

    public String getCountry() {
        return mCountry;
    }
}
