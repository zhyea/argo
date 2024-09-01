// auth store

import {defineStore} from 'pinia'
import {doLogin, doLogout, setHttpToken, removeHttpToken} from '@/api/auth'
import {config} from '@/config/index.js'


export const useAuthStore = defineStore('auth', {

	state: () => ({
		token: sessionStorage.getItem(config.TOKEN),
	}),

	actions: {

		// set token function
		setToken(token) {
			this.token = token
			sessionStorage.setItem(config.TOKEN, token)
			setHttpToken(token)
		},


		// remove token function
		removeToken(token) {
			this.token = ''
			sessionStorage.removeItem(config.TOKEN)
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
						console.log(error)
						reject(error)
					})
			})
		},


		// logout function
		async useLogout() {
			return new Promise((resolve, reject) => {
				return doLogout()
					.then(response => {
						this.removeToken()
						resolve()
					}).catch(error => {
						reject(error)
					})
			})
		},

		// add more actions here...
	}

})
