// auth store

import {defineStore} from 'pinia'
import {doLogin, doLogout, setHttpToken, removeHttpToken, doPing} from '@/api/auth'
import {route} from '@/config'
import {cacheToken, removeCachedToken} from "@/utils/localforage";


export const useAuthStore = defineStore('auth', {

	state: () => ({
		//token: sessionStorage.getItem(route.TOKEN),
		token: '',
	}),

	actions: {

		// set token function
		setToken(token: string) {
			this.token = token
			//sessionStorage.setItem(route.TOKEN, token)
			setHttpToken(token)
		},


		// remove token function
		removeToken() {
			this.token = ''
			//sessionStorage.removeItem(route.TOKEN)
			removeHttpToken()
		},


		// login function
		async useLogin(data: any) {
			return new Promise((resolve, reject) => {
				return doLogin(data)
					.then(response => {
						const token = response.data;

						this.setToken(token);
						const r = cacheToken(token)

						resolve(r)
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

						this.removeToken()
						removeCachedToken()

						resolve(response)
					}).catch(error => {
						reject(error)
					})
			})
		},


		// ping function
		async usePing() {
			return new Promise((resolve, reject) => {
				return doPing().then(response => {
					resolve(response)
				}).catch((error: any) => {
					reject(error)
				})
			})
		}

		// add more actions here...
	}

})
