/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package org.jboss.fuse.forge.addon.commands;

import javax.inject.Inject;

import org.jboss.forge.addon.maven.plugins.ConfigurationElementBuilder;
import org.jboss.forge.addon.ui.context.UIBuilder;
import org.jboss.forge.addon.ui.context.UIContext;
import org.jboss.forge.addon.ui.input.UIInput;
import org.jboss.forge.addon.ui.metadata.UICommandMetadata;
import org.jboss.forge.addon.ui.metadata.WithAttributes;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.result.Results;
import org.jboss.forge.addon.ui.util.Categories;
import org.jboss.forge.addon.ui.util.Metadata;

public class ExcludeConfigNamesCommand extends AbstractAddRegExCommand {

    @Inject
    @WithAttributes(label = "Exclude Argument Name RegEx", required = true, description = "Regular expression for arguments to exclude")
    private UIInput<String> excludeConfigNames;

    @Override
	public UICommandMetadata getMetadata(UIContext context) {
		return Metadata
				.forCommand(ExcludeConfigNamesCommand.class)
				.name("Api Component: Exclude Config Names")
				.category(Categories.create("Camel"));
	}

	@Override
	public void initializeUI(UIBuilder builder) throws Exception {
		super.initializeUI(builder);
        builder.add(excludeConfigNames);
	}

	@Override
	protected String getConfigElementName() {
		return "excludeConfigNames";
	}

	@Override
	protected Result doConfigure(ConfigurationElementBuilder configElement) {
        configElement.setText(processRegexParam(configElement.getText(), excludeConfigNames));
        return Results.success("Command 'Api Component: Exclude Config Names' successfully executed!");
	}
}