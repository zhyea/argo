// auth store

import {defineStore} from 'pinia'
import {doLogin, doLogout, doPing, removeHttpToken, setHttpToken} from '@/api/auth'
import {cacheToken, removeCachedToken} from '@/utils/cache';


export const useAuthStore = defineStore('auth', {

	state: () => ({
		//token: sessionStorage.getItem(route.TOKEN),
		token: '',
	}),

	actions: {

		// set token function
		setToken(token: string) {
			this.token = token
			setHttpToken(token);
			cacheToken(token)
		},


		// remove token function
		removeToken() {
			this.token = ''
			removeCachedToken();
			removeHttpToken()
		},


		// login function
		async useLogin(data: any) {
			try {
				const response = await doLogin(data);
				const token = response.data;
				this.setToken(token);
			} catch (error) {
				throw error;
			}
		},


		// logout function
		async useLogout() {
			try {
				await doLogout();
				this.removeToken();
				return Promise.resolve();
			} catch (error) {
				return Promise.reject(error);
			}
		},


		// ping function
		async usePing() {
			try {
				const response = await doPing();
				return Promise.resolve(response);
			} catch (error) {
				return Promise.reject(error);
			}
		}

		// add more actions here...
	}

})
