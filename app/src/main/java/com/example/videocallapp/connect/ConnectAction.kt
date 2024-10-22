package com.example.videocallapp.connect

interface ConnectAction {

    data class OnNameChange(val name: String) : ConnectAction
    data object OnConnectClick: ConnectAction
}
