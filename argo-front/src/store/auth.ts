// auth store

import {defineStore} from 'pinia'
import {doLogin, doLogout, doPing, removeHttpToken, setHttpToken} from '@/api/auth'
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
			try {
				const response = await doLogin(data);
				const token = response.data;
				this.setToken(token);
				return await cacheToken(token);
			} catch (error) {
				throw error;
			}
		},


		// logout function
		async useLogout() {
			try {
				await doLogout();
				this.removeToken();
				await removeCachedToken();
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
