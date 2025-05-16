import config from "@/config";
import localforage from "localforage";

const TOKEN = 'token:'
const PERMISSION = 'permissions:'
const LOCALE = 'locale:'

export const setLocale = (lang: string) => {
	return localforage.setItem(getLocaleKey(), lang)
}

export const getLocale = () => {
	return localforage.getItem(getLocaleKey())
}

const getLocaleKey = () => {
	return LOCALE + config.guard
}

export const cacheToken = (token: string) => {
	return localforage.setItem(getTokenKey(), token)
}

export const getCachedToken = () => {
	return localforage.getItem(getTokenKey())
}

export const removeCachedToken = () => {
	return localforage.removeItem(getTokenKey())
}

export const getTokenKey = () => {
	return TOKEN + config.guard
}