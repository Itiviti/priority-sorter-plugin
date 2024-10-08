/*
 * The MIT License
 *
 * Copyright (c) 2013, Magnus Sandberg
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package jenkins.advancedqueue.priority.strategy;

import hudson.Extension;
import hudson.cli.BuildCommand.CLICause;
import hudson.model.Cause;
import hudson.model.Queue;
import java.util.List;
import jenkins.advancedqueue.Messages;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Magnus Sandberg
 * @since 2.0
 */
public class CLICauseStrategy extends AbstractStaticPriorityStrategy {

    @Extension
    public static class UserIdCauseStrategyDescriptor extends AbstractStaticPriorityStrategyDescriptor {

        public UserIdCauseStrategyDescriptor() {
            super(Messages.Job_triggered_from_CLI());
        }
    }

    @DataBoundConstructor
    public CLICauseStrategy(int priority) {
        setPriority(priority);
    }

    @Override
    public boolean isApplicable(Queue.Item item) {
        List<Cause> causes = item.getCauses();
        for (Cause cause : causes) {
            if (cause instanceof CLICause) {
                return true;
            }
        }
        return false;
    }
}
