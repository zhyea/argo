import config from "@/config";

const TOKEN = 'token:'
const PERMISSION = 'permissions:'
const LOCALE = 'locale:'

export function setLocale(lang: string) {
	return sessionStorage.setItem(getLocaleKey(), lang)
}

export async function getLocale() {
	return sessionStorage.getItem(getLocaleKey())
}

function getLocaleKey() {
	return LOCALE + config.guard
}

export function cacheToken(token: string) {
	return sessionStorage.setItem(keyOfToken(), token)
}

export function getCachedToken(): string {
	try {
		const token = sessionStorage.getItem(keyOfToken());
		if (token) {
			return token;
		}
		return '';
	} catch (error) {
		console.error('Failed to get cached token:', error);
		throw new Error('Failed to retrieve cached token');
	}
}

export function removeCachedToken() {
	return sessionStorage.removeItem(keyOfToken())
}

export function keyOfToken() {
	return TOKEN + config.guard
}