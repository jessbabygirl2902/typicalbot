/**
 * Copyright 2016-2018 Bryan Pikaard & Nicholas Sylke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.typicalbot.command.core;

import com.typicalbot.command.Command;
import com.typicalbot.command.CommandArgument;
import com.typicalbot.command.CommandCategory;
import com.typicalbot.command.CommandConfiguration;
import com.typicalbot.command.CommandContext;
import com.typicalbot.command.CommandPermission;
import com.typicalbot.shard.Shard;
import com.typicalbot.util.StringUtil;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@CommandConfiguration(category = CommandCategory.CORE, aliases = {"commands", "cmds"})
public class CommandsCommand implements Command {
    @Override
    public CommandPermission permission() {
        return CommandPermission.GUILD_MEMBER;
    }

    @Override
    public void execute(CommandContext context, CommandArgument argument) {
        Set<Command> commands = Shard.getSingleton().getCommandManager().getCommands();

        EmbedBuilder builder = new EmbedBuilder();

        builder.setTitle("Commands");

        for (CommandCategory category : CommandCategory.values()) {
            builder.addField(StringUtil.firstUpperCase(category.name()), commands.stream()
                    .filter(c -> c.getConfiguration().category().equals(category))
                    .sorted(Comparator.comparing((Command x) -> x.getConfiguration().aliases()[0]).thenComparing((Command y) -> y.getConfiguration().aliases()[0]))
                    .map(c -> c.getConfiguration().aliases()[0])
                    .collect(Collectors.joining(", ")), false);
        }

        context.sendEmbed(builder.build());
    }
}
