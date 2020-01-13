/*
 * ************************************************************************
 *  AppInstance.kt
 * *************************************************************************
 * Copyright © 2020 VLC authors and VideoLAN
 * Author: Nicolas POMEPUY
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston MA 02110-1301, USA.
 * **************************************************************************
 *
 *
 */

package org.videolan.resources

import android.app.Application
import android.content.Context
import android.content.res.Resources
import java.lang.reflect.InvocationTargetException

object AppContextProvider {
    lateinit var _appContext: Context

    val appContext: Context
        get() {
            return if (::_appContext.isInitialized)
                _appContext
            else {
                try {
                    _appContext = Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication").invoke(null) as Application
                } catch (ignored: IllegalAccessException) {
                } catch (ignored: InvocationTargetException) {
                } catch (ignored: NoSuchMethodException) {
                } catch (ignored: ClassNotFoundException) {
                } catch (ignored: ClassCastException) {
                }
                _appContext
            }
        }

    /**
     * @return the main resources from the Application
     */
    val appResources: Resources
        get() = appContext.resources
}