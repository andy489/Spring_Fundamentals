#!/bin/bash

for d in ./* ; do
	for dd in "${d}"/* ; do
		case "${dd}" in *App)
			for ddd in "${dd}"/* ; do
			
				case ${ddd} in *.md) 
					rm "${ddd}"
				esac

				case ${ddd} in *.bat) 
					rm "${ddd}"
				esac

				case ${ddd} in *settings.gradle)
					rm "${ddd}"
				esac

				case ${ddd} in *build)
					rm -r "${ddd}"
				esac

				case ${ddd} in *bin)
					rm -r "${ddd}"
				esac

				case ${ddd} in *gradlew)
					rm "${ddd}"
				esac

				case ${ddd} in */gradle)
					rm -r "${ddd}"
				esac

				case ${ddd} in .iml)
					rm "${ddd}"
				esac
			done 
		esac
	done
done