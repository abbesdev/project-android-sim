package com.abbes.schoolspace.adminscreeens

import com.framgia.library.calendardayview.data.IEvent
import java.util.*


/**
 * Created by FRAMGIA\pham.van.khac on 07/07/2016.
 */
class Event : IEvent {
    var id: Long = 0
    private var mStartTime: Calendar? = null
    private var mEndTime: Calendar? = null
    private var mName: String? = null
    var location: String? = null
    private var mColor = 0

    constructor() {}
    constructor(
        mId: Long, mStartTime: Calendar?, mEndTime: Calendar?, mName: String?, mLocation: String?,
        mColor: Int
    ) {
        id = mId
        this.mStartTime = mStartTime
        this.mEndTime = mEndTime
        this.mName = mName
        location = mLocation
        this.mColor = mColor
    }

    override fun getStartTime(): Calendar {
        return mStartTime!!
    }

    fun setStartTime(startTime: Calendar?) {
        mStartTime = startTime
    }

    override fun getEndTime(): Calendar {
        return mEndTime!!
    }

    fun setEndTime(endTime: Calendar?) {
        mEndTime = endTime
    }

    override fun getName(): String {
        return mName!!
    }

    fun setName(name: String?) {
        mName = name
    }

    override fun getColor(): Int {
        return mColor
    }

    fun setColor(color: Int) {
        mColor = color
    }
}