/*
 *  Copyright 2017 TWO SIGMA OPEN SOURCE, LLC
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.twosigma.beakerx.kernel.magic.command.functionality;

import com.twosigma.beakerx.kernel.Code;
import com.twosigma.beakerx.kernel.KernelFunctionality;
import com.twosigma.beakerx.kernel.magic.command.item.MagicCommandResultItem;
import com.twosigma.beakerx.message.Message;

public class TimeItLineModeMagicCommand extends TimeMagicCommand {
  public static final String TIMEIT_LINE = "%timeit";

  public TimeItLineModeMagicCommand(KernelFunctionality kernel) {
    super(kernel);
  }

  @Override
  public MagicCommandResultItem execute(Code code, String command, Message message, int executionCount) {
    String codeToExecute = code.asString().replace(TIMEIT_LINE, "");
    codeToExecute = codeToExecute.replaceAll("(-.)(\\d*\\s)", "");
    try {
      return timeIt(buildTimeItOption(code), codeToExecute, message, executionCount);
    } catch (IllegalArgumentException e) {
      return MagicCommandUtils.errorResult(message, e.getMessage(), executionCount, kernel);
    }
  }
}