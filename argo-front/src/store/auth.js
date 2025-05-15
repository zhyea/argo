// auth store

import {defineStore} from 'pinia'
import {doLogin, doLogout, setHttpToken, removeHttpToken, doPing} from '@/api/auth'
import {route} from '@/config'


export const useAuthStore = defineStore('auth', {

	state: () => ({
		token: sessionStorage.getItem(route.TOKEN),
	}),

	actions: {

		// set token function
		setToken(token) {
			this.token = token
			sessionStorage.setItem(route.TOKEN, token)
			setHttpToken(token)
		},


		// remove token function
		removeToken() {
			this.token = ''
			sessionStorage.removeItem(route.TOKEN)
			removeHttpToken()
		},


		// login function
		async useLogin(data) {
			return new Promise((resolve, reject) => {
				return doLogin(data)
					.then(response => {
						this.setToken(response.data)
						resolve()
					}).catch(error => {
						reject(error)
					})
			})
		},


		// logout function
		async useLogout() {
			return new Promise((resolve, reject) => {
				this.removeToken()
				return doLogout()
					.then(response => {
						console.log(response)
						resolve(response)
					}).catch(error => {
						reject(error)
					})
			})
		},


		// ping function
		async usePing() {
			return new Promise((resolve, reject) => {
				return doPing().catch(error => {
					this.removeToken()
				})
			})
		}

		// add more actions here...
	}

})
