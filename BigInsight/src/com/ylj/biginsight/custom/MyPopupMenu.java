/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ylj.biginsight.custom;

import android.content.Context;
import android.view.View;
import android.widget.PopupMenu;

/**
 * A PopupMenu displays a {@link Menu} in a modal popup window anchored to a {@link View}.
 * The popup will appear below the anchor view if there is room, or above it if there is not.
 * If the IME is visible the popup will not overlap it until it is touched. Touching outside
 * of the popup will dismiss it.
 */
public class MyPopupMenu extends PopupMenu {

	public MyPopupMenu(Context context, View anchor) {
		super(context, anchor);
		// TODO Auto-generated constructor stub
	}
   
}
