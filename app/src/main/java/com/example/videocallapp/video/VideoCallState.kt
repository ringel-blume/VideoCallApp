package com.example.videocallapp.video

import java.lang.Error

data class VideoCallState(
    val call: Call,
    val callState: CallState? = null,
    val error: String? = null
)
enum class CallState{
    JOINING,
    ACTIVE,
    ENDED
}