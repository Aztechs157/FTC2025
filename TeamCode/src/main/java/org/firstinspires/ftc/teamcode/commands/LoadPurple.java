package org.firstinspires.ftc.teamcode.commands;

import com.arcrobotics.ftclib.command.CommandBase;

public class LoadPurple extends CommandBase {
    private final SpindexerSystem spindexer;

    public LoadPurple(final SpindexerSystem spindexer) {
        this.spindexer = spindexer;
        addRequirements(spindexer);
    }

    @Override
    public void initialize() {}

    @Override
    public boolean isFinished() {}

}