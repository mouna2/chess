/*
	PieceSelectionPanel - The panel to select a piece from to setup a position.

	Copyright (C) 2003 The Java-Chess team <info@java-chess.de>

	This program is free software; you can redistribute it and/or
	modify it under the terms of the GNU General Public License
	as published by the Free Software Foundation; either version 2
	of the License, or (at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program; if not, write to the Free Software
	Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/

package de.java_chess.javaChess.dialogs;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import de.java_chess.javaChess.piece.Piece;
import de.java_chess.javaChess.renderer2d.*;
import de.java_chess.javaChess.util.*;


/**
 * Copyright:     Copyright (c) 2003 The Java-Chess team <info@java-chess.de>
 * Organisation:  The Java-Chess team
 * @author:       Faber
 * @version 1.0
 */

public class PieceSelectionPanel extends JPanel implements Piece, ActionListener {

		private SetupBoardDialog setupBoardDialog;
		private GridBagLayout gblPieces = new GridBagLayout();

		private JButton [] [] _pieceButton;

		private ChessSet _set = new ChessSet( this);

		private ImageIcon [] [] _pieceIcon;

		private JLabel jlSelectedPiece = new JLabel( "Selected piece:" );

		private JLabel jlSelectedPieceImage = new JLabel();

		/**
		 * The type and color of a piece.
		 */
		private byte pieceColor;

		/**
		 * Constructor
		 */
		public PieceSelectionPanel() {

	_pieceIcon = new ImageIcon[2][7];
	for( byte col = 0; col <= 1; col++) {
			for( byte type=1; type <= 6; type++) {
		_pieceIcon[col][type] = new ImageIcon( new PieceRenderer( col, type, _set, this));
			}
	}

	_pieceButton = new JButton[2][7];
	for( byte col = 0; col <= 1; col++) {
			for( byte type = 1; type <= 6; type++) {
		_pieceButton[col][type] = new JButton( _pieceIcon[col][type]);
		_pieceButton[col][type].addActionListener( this);
			}
	}

//	this.jlSelectedPieceImage.setIcon( _pieceIcon[this.getColor()][this.getType()] );

	// -----------------------------------------------------------------------
	// Layout:
	// -----------------------------------------------------------------------
	this.setLayout(gblPieces);
	this.add( _pieceButton[Piece.BLACK][Piece.PAWN]
			, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
						 , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
	this.add( _pieceButton[ Piece.WHITE][Piece.PAWN]
		 , new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0
						, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.BLACK][Piece.KNIGHT]
		 , new GridBagConstraints(1, 0, 2, 1, 1.0, 0.0
						, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.BLACK][Piece.BISHOP]
			, new GridBagConstraints(3, 0, 1, 1, 1.0, 0.0
						 , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.BLACK][Piece.ROOK]
			, new GridBagConstraints(4, 0, 1, 1, 1.0, 0.0
						 , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.BLACK][Piece.QUEEN]
		 , new GridBagConstraints(5, 0, 1, 1, 1.0, 0.0
						, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.BLACK][Piece.KING]
			, new GridBagConstraints(6, 0, 1, 1, 1.0, 0.0
						 , GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.WHITE][Piece.KNIGHT]
			, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
						 , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.WHITE][Piece.BISHOP]
			, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0
						 , GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.WHITE][Piece.ROOK]
		 ,  new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
						 ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add( _pieceButton[Piece.WHITE][Piece.QUEEN]
			, new GridBagConstraints(5, 1, 1, 1, 0.0, 0.0
						 ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
	this.add(_pieceButton[Piece.WHITE][Piece.KING]
		 , new GridBagConstraints(6, 1, 1, 1, 0.0, 0.0
						,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

	this.add(jlSelectedPiece, new GridBagConstraints(7, 0, 1, 2, 1.0, 1.0
							 ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));
	this.add(jlSelectedPieceImage, new GridBagConstraints(8, 0, 1, 2, 1.0, 1.0
						,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(4, 4, 4, 4), 0, 0));

		} // Constructor

		public void setCallingDialog(SetupBoardDialog dialog) {
	this.setupBoardDialog = dialog;
		}

		public void actionPerformed( ActionEvent e) {
	for( byte col = 0; col <= 1; col++) {
			for( byte type = 1; type <= 6; type++) {
		if( _pieceButton[col][type] == e.getSource()) {
				this.setType( type);
				this.setColor( col);
				this.jlSelectedPieceImage.setIcon( _pieceIcon[col][type]);
				return;
		}
			}
	}
		}

		public Image getSelectedPieceImage() {
	byte type = this.getType();
	if( type == 0) {
			type = Piece.KING;
	}

	// To avoid the instanciation of a image each time, just reuse
	// the images of the icons.
	return _pieceIcon[this.getColor()][type].getImage();
		}

		// -----------------------------------------------------------------------
		// Interface methods:
		// -----------------------------------------------------------------------

		/**
		 * Get the type of this piece.
		 *
		 * @return The type of this piece.
		 */
		public byte getType() {
	return ( (byte)(pieceColor >> 1) );
		}

		/**
		 * Set the type of this piece.
		 *
		 * @param type The type of this piece as defined as
		 *             constants in the Piece interface.
		 */
		 public void setType(byte type) {
	 pieceColor &= (byte)1;
	 pieceColor |= (type << 1);
		 }

		/**
		 * Get the color of this piece.
		 *
		 * @return The color of this piece.
		 */
		public byte getColor() {
	return ( (byte)(pieceColor & (byte)1) );
		}

		/**
		 * Set the color of this piece.
		 *
		 * @param The new color of this piece.
		 */
		public void setColor(byte color) {
	pieceColor &= (byte)14;
	pieceColor |= color;
		}

		/**
		 * Check, if this piece is white.
		 *
		 * @return true, if this piece is white, false if black.
		 */
		public boolean isWhite() {
	return (( this.pieceColor & 1) != 0 );
		}

		/**
		 * Get type and color as 1 byte.
		 *
		 * @return Type and color and 1 byte.
		 */
		public byte getTypeAndColor() {
	return ( this.pieceColor );
		}

	/**
	 * Sets an icon for the SelectedIconLabel when no icon has been set before
	 */
		public void initializeSelectedIconLabel()
		{
			if ( this.jlSelectedPieceImage.getIcon() == null )
			{
				byte type = this.getType();
				if( type == 0)
						type = Piece.KING;

				this.jlSelectedPieceImage.setIcon( _pieceIcon[this.getColor()][type] );
			}
		}
}
